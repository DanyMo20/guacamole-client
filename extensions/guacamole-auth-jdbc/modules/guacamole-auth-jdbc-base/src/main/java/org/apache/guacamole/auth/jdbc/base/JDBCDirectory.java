/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.auth.jdbc.base;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.AtomicDirectoryOperation;
import org.apache.guacamole.net.auth.Directory;
import org.apache.guacamole.net.auth.Identifiable;
import org.mybatis.guice.transactional.Transactional;

/**
 * An implementation of Directory that uses database transactions to guarantee
 * atomicity for any operations supplied to tryAtomically().
 */
public abstract class JDBCDirectory<ObjectType extends Identifiable>
        extends RestrictedObject implements Directory<ObjectType>  {

    @Override
    @Transactional
    public void tryAtomically(AtomicDirectoryOperation<ObjectType> operation)
            throws GuacamoleException {

        // Execute the operation atomically - the @Transactional annotation
        // specifies that the entire operation will be performed in a transaction
        operation.executeOperation(true, this);

    }
}
