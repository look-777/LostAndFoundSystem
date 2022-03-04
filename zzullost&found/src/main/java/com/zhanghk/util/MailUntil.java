package com.zhanghk.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * 发送邮件服务接口
 */
public class MailUntil {

    public void SendMail(String sender,String rev,String content) throws Exception {
        //1.设置创建session需要的属性
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.auth", "true");
        //安全认证
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        //2.根据配置创建会话对象，用于和邮件服务交互,构造器被私有化，用其他方法创建对象
        Session session=Session.getInstance(props);
        //设置为debug模式，可以查看详细的发送log
        session.setDebug(true);

        //3.创建一封邮件，调用自己写的生成邮件的方法
        MimeMessage message=creatMimeMessage(session,sender,rev,content);
        //4.根据session创建传输对象
        Transport transport=session.getTransport();
        transport.connect("3250514239@qq.com","vwmghvuwtvhbdabg");
        //6.发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        //7.关闭连接
        transport.close();

    }

    /**
     * 创建一封只包含文本的简单邮件
     */
    public static MimeMessage creatMimeMessage(Session session,String sendMail, String receiveMail,String content) throws Exception{

        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "ZZU寻物小帮手", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "普通收件人", "UTF-8"));
        message.setSubject("系统通知","UTF-8");
        message.setContent(content,"text/html;charset=utf-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

}
