import java.rmi.*;
import java.io.*;

public class QueryClient {
    public static void main(String[] args) {
        try {
        	int RMIPort;
            String hostName;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            hostName = (br.readLine()).trim();
            if(hostName==null) {
            	hostName = "localhojst";
            }
            System.out.println("Enter the RMIregistry port number:");
            String portNum = (br.readLine()).trim();
            if(portNum==null) portNum = "13";
            RMIPort = Integer.parseInt(portNum);
            String registryURL = 
               "rmi://" + hostName+ ":" + RMIPort + "/query";
            //去注册中心查找需要调用的对象
            // find the remote object and cast it to an interface object
            QueryInterface h =
              (QueryInterface)Naming.lookup(registryURL);
            System.out.println("Lookup completed " );
            String queryUser;
            String name;
            String prompt;
            while(true) {
            	System.out.println("teacher or student info you want to "
            			+ "query:? or you can type 'q' to quit\n");
            	if((prompt=(br.readLine()).trim()).equals("q")) break;
            	else if(prompt!=null) {
            		queryUser = prompt;
            		System.out.println("what is the name:?\n");
					while((name=(br.readLine()).trim())==null) {
		            	System.out.println("you are not entering anything");
		            }
		            if("teacher".equals(queryUser)) {
		            	int age = h.queryTeacherAge(name);
		            	String sex = h.queryTeacherSex(name);
		            	System.out.println("teacher "+name+" sex:"+sex+" age: "+age);
		            } else if("student".equals(queryUser)) {
		            	float grade = h.queryStudentGrade(name);
		            	int age = h.queryStudentAge(name);
		            	System.out.println("student "+name+" grade:"+grade+" age: "+age);
		            } else {
		            	System.out.println("you are entering wrong query typen\n");
		            }
            	}
            }
            
            
         } // end try
         catch (Exception e) {
            System.out.println("Exception in HelloClient: " + e);
         }
    }
}