package emailTest;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
/** 
 * Title: 使用javamail发送邮件 
 * Description: 演示如何使用javamail包发送电子邮件。这个实例可发送多附件 Filename: 
 * Mail.java 
 */  
public class JavaMail {
	String to = "";// 收件人  
    String from = "";// 发件人  
    String host = "";// smtp主机  
    String username = "";  
    String password = "";  
    String filename = "";// 附件文件名  
    String subject = "";// 邮件主题  
    String content = "";// 邮件正文  
    @SuppressWarnings("unchecked")  
    Vector file = new Vector();// 附件文件集合  
  
    /** 
     *方法说明：默认构造器 输入参数： 返回类型： 
     */  
    public JavaMail() {  
    }  
  
    /** 
     *方法说明：构造器，提供直接的参数传入 输入参数： 返回类型： 
     */  
    public JavaMail(String to, String from, String smtpServer, String username,  
            String password, String subject, String content) {  
        this.to = to;  
        this.from = from;  
        this.host = smtpServer;  
        this.username = username;  
        this.password = password;  
        this.subject = subject;  
        this.content = content;  
    }  
  
    /** 
     *方法说明：设置邮件服务器地址 输入参数：String host 邮件服务器地址名称 返回类型： 
     */  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    /** 
     *方法说明：设置登录服务器校验密码 输入参数： 返回类型： 
     */  
    public void setPassWord(String pwd) {  
        this.password = pwd;  
    }  
  
    /** 
     *方法说明：设置登录服务器校验用户 输入参数： 返回类型： 
     */  
    public void setUserName(String usn) {  
        this.username = usn;  
    }  
  
    /** 
     *方法说明：设置邮件发送目的邮箱 输入参数： 返回类型： 
     */  
    public void setTo(String to) {  
        this.to = to;  
    }  
  
    /** 
     *方法说明：设置邮件发送源邮箱 输入参数： 返回类型： 
     */  
    public void setFrom(String from) {  
        this.from = from;  
    }  
  
    /** 
     *方法说明：设置邮件主题 输入参数： 返回类型： 
     */  
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
  
    /** 
     *方法说明：设置邮件内容 输入参数： 返回类型： 
     */  
    public void setContent(String content) {  
        this.content = content;  
    }  
  
    /** 
     *方法说明：把主题转换为中文 输入参数：String strText 返回类型： 
     */  
    public String transferChinese(String strText) {  
        try {  
            strText = MimeUtility.encodeText(new String(strText.getBytes(),  
                    "GB2312"), "GB2312", "B");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return strText;  
    }  
  
    /** 
     *方法说明：往附件组合中添加附件 输入参数： 返回类型： 
     */  
    public void attachfile(String fname) {  
        file.addElement(fname);  
    }  
  
    /** 
     *方法说明：发送邮件 输入参数： 返回类型：boolean 成功为true，反之为false 
     */  
    public boolean sendMail() {  
  
        // 构造mail session  
        Properties props = System.getProperties();  
        props.put("mail.smtp.host", host);  
        props.put("mail.smtp.auth", "true");  
        Session session = Session.getDefaultInstance(props,  
                new Authenticator() {  
                    public PasswordAuthentication getPasswordAuthentication() {  
                        return new PasswordAuthentication(username, password);  
                    }  
                });  
  
        try {  
            // 构造MimeMessage 并设定基本的值  
            MimeMessage msg = new MimeMessage(session);  
            msg.setFrom(new InternetAddress(from));  
            InternetAddress[] address = { new InternetAddress(to) };  
            msg.setRecipients(Message.RecipientType.TO, address);  
            subject = transferChinese(subject);  
            msg.setSubject(subject);  
  
            // 构造Multipart  
            Multipart mp = new MimeMultipart();  
  
            // 向Multipart添加正文  
            MimeBodyPart mbpContent = new MimeBodyPart();  
            mbpContent.setText(content);  
            // 向MimeMessage添加（Multipart代表正文）  
            mp.addBodyPart(mbpContent);  
  
            // 向Multipart添加附件  
            Enumeration efile = file.elements();  
            while (efile.hasMoreElements()) {  
  
                MimeBodyPart mbpFile = new MimeBodyPart();  
                filename = efile.nextElement().toString();  
                FileDataSource fds = new FileDataSource(filename);  
                mbpFile.setDataHandler(new DataHandler(fds));  
                mbpFile.setFileName(fds.getName());  
                // 向MimeMessage添加（Multipart代表附件）  
                mp.addBodyPart(mbpFile);  
  
            }  
  
            file.removeAllElements();  
            // 向Multipart添加MimeMessage  
            msg.setContent(mp);  
            msg.setSentDate(new Date());  
            // 发送邮件  
            Transport.send(msg);  
  
        } catch (MessagingException mex) {  
            mex.printStackTrace();  
            Exception ex = null;  
            if ((ex = mex.getNextException()) != null) {  
                ex.printStackTrace();  
            }  
            return false;  
        }  
        return true;  
    }  
  
    /** 
     *方法说明：主方法，用于测试 输入参数： 返回类型： 
     */  
    public static void main(String[] args) {  
    	JavaMail sendmail = new JavaMail();  
        sendmail.setHost("smtp.sohu.com");  
        sendmail.setUserName("du_jiang");  
        sendmail.setPassWord("31415926");  
        sendmail.setTo("dujiang@sricnet.com");  
        sendmail.setFrom("du_jiang@sohu.com");  
        sendmail.setSubject("你好，这是测试！");  
        sendmail.setContent("你好这是一个带多附件的测试！");  
        // Mail sendmail = new  
        // Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","你好","胃，你好吗？");  
        sendmail.attachfile("c:\\test.txt");  
        sendmail.attachfile("DND.jar");  
        sendmail.sendMail();  
  
    }  
}
