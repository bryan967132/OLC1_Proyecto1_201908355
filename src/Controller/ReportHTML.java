package Controller;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import Components.ErrorL;
import Components.Response;
public class ReportHTML {
    public void reportErrors(int index, String name, ArrayList<ErrorL> errorsL, ArrayList<Response> errorsS) {
        String firstHTML = "<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Cadenas.olc</title>\n" +
"    <style>\n" +
"        body {\n" +
"            font-family: \"Arial\";\n" +
"            font-size: larger;\n" +
"        }\n" +
"        table {\n" +
"            border-collapse: collapse;\n" +
"            margin: 10px 0;\n" +
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
"        .rowMain {\n" +
"            background-color: #009900;\n" +
"            color: white;\n" +
"        }\n" +
"    </style>\n" +
"</head>\n" +
"<body>\n";
        String finalHTML = "</body>\n" +
"</html>";
        String firstTABLE = "\t<div class=\"centerH\">\n" +
"        <table class=\"table\">\n" +
"            <tr class=\"rowMain\">\n" +
"                <td colspan=\"3\">Errores Léxicos</td>\n" +
"            </tr>\n" +
"            <tr class=\"rowMain\">\n" +
"                <td>Caracter Desconocido</td>\n" +
"                <td>Línea</td>\n" +
"                <td>Columna</td>\n" +
"            </tr>\n";
        String firstTABLE1 = "\t<div class=\"centerH\">\n" +
"        <table class=\"table\">\n" +
"            <tr class=\"rowMain\">\n" +
"                <td>Errores Sintácticos</td>\n" +
"            </tr>\n" +
"            <tr class=\"rowMain\">\n" +
"                <td>Descripción</td>\n" +
"            </tr>\n";
        String finalTABLE = "\t\t</table>\n" +
"    </div>\n";
        String html = firstHTML + (errorsL.size() > 0 ? firstTABLE + getHTML(errorsL) + finalTABLE : "") + (errorsS.size() > 0 ? firstTABLE1 + getHTMLS(errorsS) + finalTABLE : "") + finalHTML;
        buildHTML("ERRORES_201908355", "errors_" + index + "_" + name.replace(".olc", ""), html);
    }
    private String getHTML(ArrayList<ErrorL> errorsL) {
        String html = "";
        for(ErrorL error : errorsL) {
            html += error.getHTML();
        }
        return html;
    }
    private String getHTMLS(ArrayList<Response> errorsS) {
        String html = "";
        for(Response error : errorsS) {
            html += error.getHTML();
        }
        return html;
    }
    private void buildHTML(String type, String name, String content) {
        try {
            File file = new File("Data/" + type);
            if(!file.exists()) {
                file.mkdirs();
            }
            file = new File("Data/" + type + "/" + name + ".html");
            FileOutputStream outputStream = new FileOutputStream("Data/" + type + "/" + name + ".html");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
            Desktop.getDesktop().browse(file.toURI());
        } catch (Exception e) {}
    }
}