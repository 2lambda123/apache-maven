package org.apache.maven.model.building;

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

import java.io.File;
import java.util.Properties;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Konstantin Perikov
 */
public class ComplexActivationTest
{

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
    private File getPom( String name )
    {
        return new File( "src/test/resources/poms/factory/" + name + ".xml" ).getAbsoluteFile();
    }

    @Test
    public void testAndConditionInActivation() throws Exception
    {
        Properties sysProperties = new Properties();
        sysProperties.setProperty( "myproperty", "test" );

        ModelBuilder builder = new DefaultModelBuilderFactory().newInstance();
        assertNotNull( builder );

        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setProcessPlugins( true );
        request.setPomFile( getPom( "complex" ) );
        request.setSystemProperties( sysProperties );

        ModelBuildingResult result = builder.build( request );
        assertNotNull( result );
        assertNotNull( result.getEffectiveModel() );
        assertEquals( "activated-1", result.getEffectiveModel().getProperties().get( "profile.file" ) );
        assertNull( result.getEffectiveModel().getProperties().get( "profile.miss" ) );
    }
    
    @Test
    public void testConditionExistingAndMissingInActivation() throws Exception
    {
    	exception.expect( ModelBuildingException.class );
    	exception.expectMessage( "Failed due to exists and missing properties are enabled for profile another-two-conditions" );
        Properties sysProperties = new Properties();
        sysProperties.setProperty("myproperty", "test");

        ModelBuilder builder = new DefaultModelBuilderFactory().newInstance();
        assertNotNull( builder );

        DefaultModelBuildingRequest request = new DefaultModelBuildingRequest();
        request.setProcessPlugins( true );
        request.setPomFile( getPom( "complexEmptyAndMissing" ) );
        request.setSystemProperties( sysProperties );
    	builder.build( request );
    }
}
