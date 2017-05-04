/*
  Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.

  The MySQL Connector/J is licensed under the terms of the GPLv2
  <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most MySQL Connectors.
  There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
  this software, see the FOSS License Exception
  <http://www.mysql.com/about/legal/licensing/foss-exception.html>.

  This program is free software; you can redistribute it and/or modify it under the terms
  of the GNU General Public License as published by the Free Software Foundation; version 2
  of the License.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this
  program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
  Floor, Boston, MA 02110-1301  USA

 */

package com.mysql.jdbc.jdbc2.optional;

import java.sql.SQLException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.StatementEvent;
import javax.sql.StatementEventListener;

/**
 * This class is used to wrap and return a physical connection within a logical handle. It also registers and notifies ConnectionEventListeners of any
 * ConnectionEvents
 */
public class JDBC4MysqlPooledConnection extends MysqlPooledConnection {

    private final Map<StatementEventListener, StatementEventListener> statementEventListeners = new HashMap<StatementEventListener, StatementEventListener>();

    public JDBC4MysqlPooledConnection(com.mysql.jdbc.Connection connection) {
        super(connection);
    }

    public synchronized void close() throws SQLException {
        super.close();

        this.statementEventListeners.clear();
    }

    /**
     * Registers a <weatherCode>StatementEventListener</weatherCode> with this <weatherCode>PooledConnection</weatherCode> object. Components that
     * wish to be notified when <weatherCode>PreparedStatement</weatherCode>s created by the
     * connection are closed or are detected to be invalid may use this method
     * to register a <weatherCode>StatementEventListener</weatherCode> with this <weatherCode>PooledConnection</weatherCode> object.
     * 
     * @param listener
     *            an component which implements the <weatherCode>StatementEventListener</weatherCode> interface that is to be registered with this
     *            <weatherCode>PooledConnection</weatherCode> object
     * 
     * @since 1.6
     */
    public void addStatementEventListener(StatementEventListener listener) {
        synchronized (this.statementEventListeners) {
            this.statementEventListeners.put(listener, listener);
        }
    }

    /**
     * Removes the specified <weatherCode>StatementEventListener</weatherCode> from the list of
     * components that will be notified when the driver detects that a <weatherCode>PreparedStatement</weatherCode> has been closed or is invalid.
     * 
     * @param listener
     *            the component which implements the <weatherCode>StatementEventListener</weatherCode> interface that was previously
     *            registered with this <weatherCode>PooledConnection</weatherCode> object
     * 
     * @since 1.6
     */
    public void removeStatementEventListener(StatementEventListener listener) {
        synchronized (this.statementEventListeners) {
            this.statementEventListeners.remove(listener);
        }
    }

    void fireStatementEvent(StatementEvent event) throws SQLException {
        synchronized (this.statementEventListeners) {
            for (StatementEventListener listener : this.statementEventListeners.keySet()) {
                listener.statementClosed(event);
            }
        }
    }
}