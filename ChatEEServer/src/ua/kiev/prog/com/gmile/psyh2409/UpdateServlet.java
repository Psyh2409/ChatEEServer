package ua.kiev.prog.com.gmile.psyh2409;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class UpdateServlet extends HttpServlet {
    private final UserSet userSet = UserSet.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User user = User.fromJSON(bufStr);
        if (user != null) {
            userSet.getUserSet().remove(user);
            userSet.add(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            }
        else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
