/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.fabric.groups;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Group<T> extends Closeable {

    /**
     * Are we connected with the cluster?
     */
    boolean isConnected();

    /**
     * Start this member
     */
    void start();

    /**
     * A member should be closed to release acquired resources used
     * to monitor the group membership.
     *
     * When the member is closed, any memberships registered via this
     * Group will be removed from the group.
     */
    void close() throws IOException;

    /**
     * Registers a listener which will be called
     * when the cluster membership changes or
     * the group is connected or disconnected.
     */
    void add(GroupListener<T> listener);

    /**
     * Removes a previously added listener.
     */
    void remove(GroupListener<T> listener);

    /**
     * Update the state of this group member.
     * If the state is null, the member will leave the group.
     *
     * This method can be called even if the group is not started,
     * in which case the state will be stored and updated
     * when the group becomes started.
     *
     * @param state the new state of this group member
     */
    void update(T state);

    /**
     * Get the list of members connected to this group.
     */
    Map<String, T> members();

    /**
     * Check if we are the master.
     */
    boolean isMaster();

    /**
     * Retrieve the master node.
     */
    T master();

    /**
     * Retrieve the list of slaves.
     */
    List<T> slaves();

}
