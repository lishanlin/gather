package Security;

import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
/**
 * 
 * @author 李山林
 * 
 * MD5 即Message-Digest Algorithm 5（信息-摘要算法 5），用于确保信息传输完整一致。是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。 
除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等
 *
 */
public class EncrypMD5 {  
      
    public byte[] eccrypt(String info) throws NoSuchAlgorithmException{  
        //根据MD5算法生成MessageDigest对象  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        byte[] srcBytes = info.getBytes();  
        //使用srcBytes更新摘要  
        md5.update(srcBytes);  
        //完成哈希计算，得到result  
        byte[] resultBytes = md5.digest();  
        return resultBytes;  
    }  
      
      
    public static void main(String args[]) throws NoSuchAlgorithmException{  
        String msg = "郭XX-精品相声技术";  
        EncrypMD5 md5 = new EncrypMD5();  
        byte[] resultBytes = md5.eccrypt(msg);  
          
        System.out.println("密文是：" + new String(resultBytes));  
        System.out.println("明文是：" + msg);  
    }  
  
}
