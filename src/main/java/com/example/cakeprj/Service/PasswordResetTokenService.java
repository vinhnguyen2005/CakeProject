package com.example.cakeprj.Service;

import com.example.cakeprj.Entity.Users;
import com.example.cakeprj.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import java.util.UUID;
import java.util.Optional;

@Service
public class PasswordResetTokenService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public PasswordResetTokenService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public boolean generatePasswordResetToken(String email) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            user.setTokenExpiry(System.currentTimeMillis() + 30 * 60 * 1000);
            userRepository.save(user);
            sendResetEmail(user.getEmail(), token);
            return true;
        }
        return false;
    }

    public boolean validatePasswordResetToken(String token) {
        Optional<Users> userOptional = userRepository.findByResetToken(token);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            if(System.currentTimeMillis() < user.getTokenExpiry()) {
                return true;
            }
        }
        return false;
    }

    public Users getUserByToken(String token) {
        return userRepository.findByResetToken(token).orElse(null);
    }

    private void sendResetEmail(String email, String token) {
        String resetLink = "http://localhost:8080/daibacbakery/password/reset?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Đặt lại mật khẩu - Đài Bắc Bakery");
        message.setText("Xin chào,\n\n"
                + "Bạn đã yêu cầu đặt lại mật khẩu của mình. Vui lòng nhấp vào liên kết bên dưới để đặt lại mật khẩu:\n\n"
                + resetLink + "\n\n"
                + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ hỗ trợ Đài Bắc Bakery");

        mailSender.send(message);
    }
}
