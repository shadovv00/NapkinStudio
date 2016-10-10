package com.napkinstudio.manager;

import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.Order;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User1 on 03.08.2016.
 */

@Service("mailService")
public class MailManager {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    public void sendEmail(Object object) {

//        if (comment.getCommText() != "")
//            comment.setCommText("Comment: " + comment.getCommText());

        MimeMessagePreparator preparator = getMessagePreparator(object);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private MimeMessagePreparator getMessagePreparator(final Object object) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                Map<String, Object> model = new HashMap<String, Object>();
                String text = "";
                Order order = null;
                if (object instanceof Comments) {
                    Comments comment = (Comments) object;
                    order = comment.getOrder();
                    String link = "http://10.4.0.129:8080/NapkinStudio/orders/" + order.getOrderId().toString();
                    helper.setTo(comment.getToUser().getEmail());



                    model.put("comment", comment);
                    model.put("link", link);

                    text = getFreeMarkerTemplateCommentContent(model);
//                if (comment.getOrder().getSAPstatus().getStatusSAPStatuseRoles() == null)
//
//                else
//                    text = getFreeMarkerTemplateCommentWithAttachmentContent(model);


                } else if (object instanceof Order) {
                    order = (Order) object;
                    String link = "http://10.4.0.129:8080/NapkinStudio/orders/" + order.getOrderId().toString();
                    helper.setTo(order.getItsUsers().get(0).getUser().getEmail());
                    model.put("order", order);
                    model.put("link", link);

                    text = getFreeMarkerTemplateOrderContent(model);//Use Freemarker or Velocity

                }
//                else if (object instanceof Map) {
//
//
//                    HashMap<Comments, MultiFile> rejectedNotification = (HashMap<Comments, MultiFile>) object;
//
//                    Comments comment = rejectedNotification.entrySet().iterator().next().getKey();
////                    MultiFile attachment = rejectedNotification.get(comment);
//                    helper.setTo(comment.getToUser().getEmail());
//                    order = comment.getOrder();
//                    model.put("comment", comment);
//
//                    text = getFreeMarkerTemplateCommentWithAttachmentContent(model);
//
//
////                    helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
//                }


                helper.setFrom("khomenkotest1@gmail.com");
                helper.setSubject(order.getOrderId() + " " + order.getItemNum() + " " + order.getPrintName());
                helper.setText(text, true);
                helper.addInline("company-logo",new ClassPathResource("img__Bunzl.png") );

                System.out.println("Template content : " + text);


            }
        };
        return preparator;
    }


    public String getFreeMarkerTemplateOrderContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_orderTemplate.ftl"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }

    public String getFreeMarkerTemplateCommentContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_commentTemplate.ftl"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }

    public String getFreeMarkerTemplateCommentWithAttachmentContent(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate("fm_commetWithArttachmentTemplate.ftl"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }
}

