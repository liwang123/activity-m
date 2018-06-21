package org.trustnote.activity.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class SendEmailUtil {

    private static final String account = "support@trustnote.org";    //登录用户名
    private static final String pass = "ThingTrust123";        //登录密码
    private static final String host = "smtp.exmail.qq.com";        //服务器地址（邮件服务器）
    private static final String port = "465";        //端口
    private static final String protocol = "smtp"; //协议

    static class MyAuthenricator extends Authenticator{
        String u = null;
        String p = null;

        public MyAuthenricator(final String u, final String p) {
            this.u=u;
            this.p=p;
        }
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.u, this.p);
        }
    }

    private final String to;    //收件人
    private final String subject;    //主题
    private final String content;    //内容
    private String fileStr;    //附件路径

    public SendEmailUtil(final String to, final String subject, final String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        //this.fileStr = fileStr;
    }



    public void send(){
        final Properties prop = new Properties();
        //协议
        prop.setProperty("mail.transport.protocol", SendEmailUtil.protocol);
        //服务器
        prop.setProperty("mail.smtp.host", SendEmailUtil.host);
        //端口
        prop.setProperty("mail.smtp.port", SendEmailUtil.port);
        //使用smtp身份验证
        prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (final GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        final Session session = Session.getDefaultInstance(prop, new MyAuthenricator(SendEmailUtil.account, SendEmailUtil.pass));
        session.setDebug(true);
        final MimeMessage mimeMessage = new MimeMessage(session);
        try {
            //发件人
            mimeMessage.setFrom(new InternetAddress(SendEmailUtil.account, "TrustNote"));
            //mimeMessage.setFrom(new InternetAddress(account));    //如果不需要就省略
            //收件人
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            //主题
            mimeMessage.setSubject(this.subject);
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类，可以包含多个MimeBodyPart对象
            final Multipart mp = new MimeMultipart();

            //MimeBodyPart可以包装文本，图片，附件
            final MimeBodyPart body = new MimeBodyPart();
            //HTML正文
            body.setContent(this.content, "text/html; charset=UTF-8");
            mp.addBodyPart(body);

            //设置邮件内容
            mimeMessage.setContent(mp);
            //仅仅发送文本
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
        } catch (final MessagingException e) {
            e.printStackTrace();
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
