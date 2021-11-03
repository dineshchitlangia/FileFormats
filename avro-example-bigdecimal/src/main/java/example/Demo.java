/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import example.avro.User;

public class Demo {
	public static void main(String[] args) throws IOException {
		User user1 = new User("Ben", 7, "red", ByteBuffer.wrap(new BigDecimal(999912321).unscaledValue().toByteArray()));
    User user2 = new User("John", 8, "gray", ByteBuffer.wrap(new BigDecimal(423233313).unscaledValue().toByteArray()));

		// Serialize user1 and user2 to disk
		File file = new File("users.avro");
		DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
		DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
		dataFileWriter.create(user1.getSchema(), file);
		dataFileWriter.append(user1);
		dataFileWriter.append(user2);
		dataFileWriter.close();

		// Deserialize Users from disk
		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
		DataFileReader<User> dataFileReader = null;
		try {
			dataFileReader = new DataFileReader<User>(file, userDatumReader);
			dataFileReader.forEach(record -> System.out.println("Manufacturing Id -> " + new BigDecimal(new BigInteger(record.getMANUFACTURERDIMID().array()))));
		} finally {
			dataFileReader.close();
		}

	}
}
