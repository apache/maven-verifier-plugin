package org.apache.maven.plugins.verifier;

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

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.verifier.model.File;

/**
 * 
 */
public class ConsoleVerificationResultPrinter
    implements VerificationResultPrinter
{
    private Log log;

    /**
     * @param log {@link Log}
     */
    public ConsoleVerificationResultPrinter( Log log )
    {
        this.log = log;
    }

    /**
     * {@inheritDoc}
     */
    public void print( VerificationResult results )
    {
        for ( File file : results.getExistenceFailures() )
        {
            printMessage( "File not found [" + file.getLocation() + "]" );
        }

        for ( File file : results.getNonExistenceFailures() )
        {
            printMessage( "File should not exist [" + file.getLocation() + "]" );
        }

        for ( File file : results.getContentFailures() )
        {
            printMessage( "File [" + file.getLocation() + "] does not match regexp [" + file.getContains() + "]" );
        }
    }

    private void printMessage( String message )
    {
        this.log.error( "[Verifier] " + message );
    }
}
