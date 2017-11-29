// A simple RMI interface file
import java.rmi.*;

//这就是stub的概念，提供本地调用远程对象方法的接口，具体的实现则是在远程服务器上的
public interface QueryInterface extends Remote {
    /**
     * This remote method returns a message.
     * @param  name - a String containing a name.
     * @return a String message.
     */
    /**远程服务对象所必须实现的方法
    * 所有的远程调用的方法,必须声明throws RemoteException
    * 从client调用此处的接口
    * */
    public int queryStudentAge(String name) throws RemoteException;
    public int queryTeacherAge(String name) throws RemoteException;
    public String queryTeacherSex(String name) throws RemoteException;
    public float queryStudentGrade(String name) throws RemoteException;

} //end interface
