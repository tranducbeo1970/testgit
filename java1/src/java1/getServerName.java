/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java1;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;

/**
 *
 * @author tranduc
 */
public class getServerName {
   public static void main(String[] args) {
        String host = "45.124.94.20";
        String user = "root";
        String password = "attech";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);

            // Bỏ qua xác minh key host
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();
            System.out.println("Đã kết nối SSH tới " + host);

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand("hostname");

            channel.setInputStream(null);
            channel.setErrStream(System.err);

            java.io.InputStream in = channel.getInputStream();
            channel.connect();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                System.out.print("Hostname: " + new String(buffer, 0, len));
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
        }
   }
}