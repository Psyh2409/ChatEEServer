package ua.kiev.prog;

import ua.kiev.prog.com.gmile.psyh2409.Room;
import ua.kiev.prog.com.gmile.psyh2409.User;
import ua.kiev.prog.com.gmile.psyh2409.UserSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AddServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();
    private UserSet userSet = UserSet.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        String sender = msg.getFrom();
        String recipient = msg.getTo();
        Room room = null;
        if (msg != null) {
            if (msg.getTo().equals("everyone")) msgList.add(msg);
            else {
                for (User u : userSet.getUserSet()) {
                    if (u.getLogin().equals(recipient)) {
                        u.getMesList().add(msg);
                        break;
                    } else {
                        for (User r : userSet.getUserSet()) {
                            if (r.getLogin().equals(sender)) {
                                if (!r.getMesList().contains(msg))
                                    r.getMesList().add(msg);
                                room = r.getRooms().get(recipient);
                                if (room != null) {
                                    if (!room.getMessages().contains(msg))
                                        room.getMessages().add(msg);
                                    for (String s : room.getUsers()) {
                                        for (User e : userSet.getUserSet()) {
                                            if (e.getLogin().equals(s)) {
                                                if (!e.getMesList().contains(msg))
                                                    e.getMesList().add(msg);
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;
        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);
        return bos.toByteArray();
    }
}
