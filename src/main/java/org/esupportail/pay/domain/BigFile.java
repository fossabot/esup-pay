/**
 * Licensed to ESUP-Portail under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 *
 * ESUP-Portail licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.esupportail.pay.domain;
import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.FetchType;

import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BigFile {
	
	//@Lob
	//@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
	@RooUploadedFile(contentType = "application/zip")
	@Basic(fetch = FetchType.LAZY)
	private Blob binaryFile;
	
	// Here we have to use directly Hibernate and no "just" JPA, because we want to use stream to read and write big files
	// @see http://stackoverflow.com/questions/10042766/jpa-analog-of-lobcreator-from-hibernate
    public void setBinaryFileStream(InputStream inputStream, long length) {		
        if (this.entityManager == null) this.entityManager = entityManager();
        Session session = (Session) this.entityManager.getDelegate(); 
        LobHelper helper = session.getLobHelper(); 
        this.binaryFile = helper.createBlob(inputStream, length); 
    }

}
