package org.apache.maven.caching.xml;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.caching.xml.buildinfo.BuildInfoType;
import org.apache.maven.caching.xml.buildinfo.io.xpp3.CacheBuildInfoXpp3Reader;
import org.apache.maven.caching.xml.buildsdiff.BuildDiffType;
import org.apache.maven.caching.xml.config.CacheType;
import org.apache.maven.caching.xml.report.CacheReportType;
import org.junit.Test;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

public class XmlServiceTest {

    @Test
    public void testConfig() throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(getClass().getResource("/cache-config-1.0.0.xsd"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setSchema(schema);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( getClass().getResource( "cache-config-instance.xml" ).toString() );

        InputStream is = getClass().getResourceAsStream( "cache-config-instance.xml" );
        final CacheType cacheConfig = new XmlService().fromInputStream( CacheType.class, is );
    }

    @Test
    public void testReport() throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(getClass().getResource("/cache-report-1.0.0.xsd"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setSchema(schema);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( getClass().getResource( "cache-report-instance.xml" ).toString() );

        InputStream is = getClass().getResourceAsStream( "cache-report-instance.xml" );
        final CacheReportType cacheReport = new XmlService().fromInputStream( CacheReportType.class, is );
    }

    @Test
    public void testBuildInfo() throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(getClass().getResource("/cache-buildinfo-1.0.0.xsd"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setSchema(schema);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( getClass().getResource( "cache-buildinfo-instance.xml" ).toString() );

        InputStream is = getClass().getResourceAsStream( "cache-buildinfo-instance.xml" );
        final BuildInfoType buildInfo = new XmlService().fromInputStream( BuildInfoType.class, is );
    }

    @Test
    public void testBuildsDiff() throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(getClass().getResource("/cache-buildsdiff-1.0.0.xsd"));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setSchema(schema);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse( getClass().getResource( "cache-buildsdiff-instance.xml" ).toString() );

        InputStream is = getClass().getResourceAsStream( "cache-buildsdiff-instance.xml" );
        final BuildDiffType buildInfo = new XmlService().fromInputStream( BuildDiffType.class, is );
    }
}