# avro-example

The __Apache Avro™ 1.8.1 Getting Started (Java)__ documentation resides at <https://avro.apache.org/docs/1.8.1/gettingstartedjava.html>. 

Its section entitled __Compiling and running the example code__, refers to example source code for java, python (py), and map reduce (ml) that resides at
<https://avro.apache.org/docs/1.8.1/examples/>

I wrote this example when I was trying to help a customer.

Problem:
When the source avro file contains logicalTypes like BigDecimal, the value does not get converted automatically.
It shows up as bytes.

Solution:
When reading the data, for logical types, we need to convert the value appropriately.

This example creates a sample avro data file with 2 records containing logical type BigDecimal.
Then it tries to read that generated avro data file and prints the values of the BigDecimal field.

You only need to run the Demo.java class to see the example in action.
If you make any changes to the schema or the Demo.java class, then execute ```mvn clean install``` and then run the Demo.java class.