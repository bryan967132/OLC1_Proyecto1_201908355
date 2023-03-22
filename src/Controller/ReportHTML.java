package Controller;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import Components.ErrorL;
public class ReportHTML {
    public void reportErrors(int index,String name,ArrayList<ErrorL> errorsL) {
        String firstHTML = "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>" + name + "</title>\n" +
"    <style>\n" +
"        body {\n" +
"            font-family: \"Arial\";\n" +
"            font-size: larger;\n" +
"        }\n" +
"        table {\n" +
"            border-collapse: collapse;\n" +
"        }\n" +
"        td {\n" +
"            border: 1px solid;\n" +
"            border-color: black;\n" +
"            padding: 5px 15px;\n" +
"        }\n" +
"        .centerH {\n" +
"            display: grid;\n" +
"            justify-content: center;\n" +
"        }\n" +
"        .centerV {\n" +
"            display: flex;\n" +
"            align-items: center;\n" +
"        }\n" +
"        .rowMain {\n" +
"            background-color: #009900;\n" +
"            color: white;\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n" +
"    <div class=\"centerH\">\n" +
"        <div class=\"centerV\">\n" +
"            <table class=\"table\">\n" +
"                <tr class=\"rowMain\">\n" +
"                    <td colspan=\"3\">Errores Léxicos</td>\n" +
"                </tr>\n" +
"                <tr class=\"rowMain\">\n" +
"                    <td>Lexema</td>\n" +
"                    <td>Línea</td>\n" +
"                    <td>Columna</td>\n" +
"                </tr>";
    String finalHTML = "</table>\n" +
"        </div>\n" +
"    </div>\n" +
"</body>\n" +
"</html>";
        String html = firstHTML + getHTML(errorsL) + finalHTML;
        buildHTML("ERRORES_201908355","errors_" + index + "_" + name.replace(".olc",""),html);
    }
    private String getHTML(ArrayList<ErrorL> errorsL) {
        String html = "";
        for(ErrorL error : errorsL) {
            html += error.getHTML();
        }
        return html;
    }
    private void buildHTML(String type,String name,String content) {
        try {
            File file = new File(type);
            if(!file.exists()) {
                file.mkdirs();
            }
            file = new File(type + "/" + name + ".html");
            FileOutputStream outputStream = new FileOutputStream(type + "/" + name + ".html");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
            Desktop.getDesktop().browse(file.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}