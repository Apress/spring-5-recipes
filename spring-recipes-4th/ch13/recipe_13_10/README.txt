1.- Download and install XMLBeans from http://www.us.apache.org/dist/xmlbeans/binaries/ 

2.- Unpack XMLBeans and take note of the unpack directory

3.- Convert the request.xml and response.xml files with XMLBeans using the following command: 

    $PATH_TO_XMLBEANS/bin/inst2xsd -design rd -enumerations never request.xml response.xml

   ($PATH_TO_XMLBEANS is where you unpacked XMLBeans)

4.- The previous command generates and XSD file named schema0.xsd

5.- temperature.xsd is a modified version of schema0.xsd with constraints for the maximum and minimum query dates
