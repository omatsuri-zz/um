/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.services;

import com.bootcamp.entities.Account;
import com.bootcamp.entities.Employee;
import com.bootcamp.repositories.AccountRepository;
import com.bootcamp.repositories.EmployeeRepository;
import java.io.IOException;
import java.util.UUID;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DevidBa
 */
@Service
public class MailService {

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    AccountRepository accountRepository;

    public void sendEmail() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("dav3rliando@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");
        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);
    }

    public String sendEmail(String email) throws MessagingException, IOException {
        String result = "";
        try {
            Employee employee = employeeRepository.getByEmail(email);
            if (employee == null) {
                return "Email not found";
            }
            String token = UUID.randomUUID().toString();
            MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(employee.getEmail());
            helper.setSubject("Hei, " + employee.getFirstName() + "! Ayo Reset Passwordmu!");
            helper.setText("<html>\n"
                    + "<head>\n"
                    + "<title></title>\n"
                    + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                    + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                    + "<style type=\"text/css\">\n"
                    + "	/* FONTS */\n"
                    + "    @media screen {\n"
                    + "		@font-face {\n"
                    + "		  font-family: 'Lato';\n"
                    + "		  font-style: normal;\n"
                    + "		  font-weight: 400;\n"
                    + "		  src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\n"
                    + "		}\n"
                    + "		\n"
                    + "		@font-face {\n"
                    + "		  font-family: 'Lato';\n"
                    + "		  font-style: normal;\n"
                    + "		  font-weight: 700;\n"
                    + "		  src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\n"
                    + "		}\n"
                    + "		\n"
                    + "		@font-face {\n"
                    + "		  font-family: 'Lato';\n"
                    + "		  font-style: italic;\n"
                    + "		  font-weight: 400;\n"
                    + "		  src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\n"
                    + "		}\n"
                    + "		\n"
                    + "		@font-face {\n"
                    + "		  font-family: 'Lato';\n"
                    + "		  font-style: italic;\n"
                    + "		  font-weight: 700;\n"
                    + "		  src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\n"
                    + "		}\n"
                    + "    }\n"
                    + "    \n"
                    + "    /* CLIENT-SPECIFIC STYLES */\n"
                    + "    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n"
                    + "    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n"
                    + "    img { -ms-interpolation-mode: bicubic; }\n"
                    + "\n"
                    + "    /* RESET STYLES */\n"
                    + "    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\n"
                    + "    table { border-collapse: collapse !important; }\n"
                    + "    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\n"
                    + "\n"
                    + "    /* iOS BLUE LINKS */\n"
                    + "    a[x-apple-data-detectors] {\n"
                    + "        color: inherit !important;\n"
                    + "        text-decoration: none !important;\n"
                    + "        font-size: inherit !important;\n"
                    + "        font-family: inherit !important;\n"
                    + "        font-weight: inherit !important;\n"
                    + "        line-height: inherit !important;\n"
                    + "    }\n"
                    + "\n"
                    + "    /* ANDROID CENTER FIX */\n"
                    + "    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\n"
                    + "\n"
                    + "<!-- HIDDEN PREHEADER TEXT -->\n"
                    + "<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n"
                    + "    Looks like you tried signing in a few too many times. Let's see if we can get you back into your account.\n"
                    + "</div>\n"
                    + "\n"
                    + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                    + "    <!-- LOGO -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#7c72dc\" align=\"center\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "                <tr>\n"
                    + "                    <td align=\"center\" valign=\"top\" style=\"padding: 40px 10px 40px 10px;\">\n"
                    + "                        <a href=\"http://litmus.com\" target=\"_blank\">\n"
                    + "                            <img alt=\"Logo\" src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/665940/helloglogo.png\" width=\"100\" height=\"100\" style=\"display: block;  font-family: 'Lato', Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;\" border=\"0\">\n"
                    + "                        </a>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <!-- HERO -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#7c72dc\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "                <tr>\n"
                    + "                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\n"
                    + "                      <h1 style=\"font-size: 32px; font-weight: 400; margin: 0;\">Trouble signing in?</h1>\n"
                    + "                    </td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <!-- COPY BLOCK -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "              <!-- COPY -->\n"
                    + "              <tr>\n"
                    + "                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n"
                    + "                  <p style=\"margin: 0;\">" + token + "</p>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "              <!-- BULLETPROOF BUTTON -->\n"
                    + "              <tr>\n"
                    + "                <td bgcolor=\"#ffffff\" align=\"left\">\n"
                    + "                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "                    <tr>\n"
                    + "                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\n"
                    + "                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "                          <tr>\n"
                    + "                              <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#7c72dc\"><a href=\"https://litmus.com\" target=\"_blank\" style=\"font-size: 20px; font-family: Helvetica, Arial, sans-serif; color: #ffffff; text-decoration: none; color: #ffffff; text-decoration: none; padding: 15px 25px; border-radius: 2px; border: 1px solid #7c72dc; display: inline-block;\">Reset Password</a></td>\n"
                    + "                          </tr>\n"
                    + "                        </table>\n"
                    + "                      </td>\n"
                    + "                    </tr>\n"
                    + "                  </table>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <!-- COPY CALLOUT -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "                <!-- HEADLINE -->\n"
                    + "                <tr>\n"
                    + "                  <td bgcolor=\"#111111\" align=\"left\" style=\"padding: 40px 30px 20px 30px; color: #ffffff; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n"
                    + "                    <h2 style=\"font-size: 24px; font-weight: 400; margin: 0;\">Unable to click on the button above?</h2>\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "                <!-- COPY -->\n"
                    + "                <tr>\n"
                    + "                  <td bgcolor=\"#111111\" align=\"left\" style=\"padding: 0px 30px 20px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n"
                    + "                    <p style=\"margin: 0;\">Click on the link below or copy/paste in the address bar.</p>\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "                <!-- COPY -->\n"
                    + "                <tr>\n"
                    + "                  <td bgcolor=\"#111111\" align=\"left\" style=\"padding: 0px 30px 40px 30px; border-radius: 0px 0px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n"
                    + "                    <p style=\"margin: 0;\"><a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #7c72dc;\">See how easy it is to get started</a></p>\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <!-- SUPPORT CALLOUT -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 30px 10px 0px 10px;\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "                <!-- HEADLINE -->\n"
                    + "                <tr>\n"
                    + "                  <td bgcolor=\"#C6C2ED\" align=\"center\" style=\"padding: 30px 30px 30px 30px; border-radius: 4px 4px 4px 4px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\n"
                    + "                    <h2 style=\"font-size: 20px; font-weight: 400; color: #111111; margin: 0;\">Need more help?</h2>\n"
                    + "                    <p style=\"margin: 0;\"><a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #7c72dc;\">We&rsquo;re here, ready to talk</a></p>\n"
                    + "                  </td>\n"
                    + "                </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "    <!-- FOOTER -->\n"
                    + "    <tr>\n"
                    + "        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\n"
                    + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\n"
                    + "              \n"
                    + "              <!-- PERMISSION REMINDER -->\n"
                    + "              <tr>\n"
                    + "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n"
                    + "                  <p style=\"margin: 0;\">You received this email because you requested a password reset. If you did not, <a href=\"http://litmus.com\" target=\"_blank\" style=\"color: #111111; font-weight: 700;\">please contact us.</a>.</p>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "              \n"
                    + "              <!-- ADDRESS -->\n"
                    + "              <tr>\n"
                    + "                <td bgcolor=\"#f4f4f4\" align=\"left\" style=\"padding: 0px 30px 30px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 18px;\" >\n"
                    + "                  <p style=\"margin: 0;\">185, Jiraeul-ro, Jijeong-myeon, Wonju-si, Gangwon-do</p>\n"
                    + "                </td>\n"
                    + "              </tr>\n"
                    + "            </table>\n"
                    + "        </td>\n"
                    + "    </tr>\n"
                    + "</table>\n"
                    + "\n"
                    + "</body>\n"
                    + "</html>", true);
            javaMailSender.send(msg);

            //Kalau berhasil, set token
            Account account = accountRepository.findById(employee.getId()).get();
            account.setToken(token);
            accountRepository.save(account);
            result = "Send email successfully";
        } catch (Exception e) {
            result = "Send email failed";
        }
        return result;
    }
}
