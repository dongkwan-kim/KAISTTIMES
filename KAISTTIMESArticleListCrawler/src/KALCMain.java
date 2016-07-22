import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

public class KALCMain {

	public static void main(String[] args) throws IOException {
		
		/*
		 * sIndex: 시작 호수
		 * eIndex: 끝 호수
		 */
		int sIndex = 400;
		int eIndex = 401;

		String outFileTitle = "articleList.tsv";
		BufferedWriter out = new BufferedWriter(new FileWriter(outFileTitle));

		for (int i = sIndex; i <= eIndex; i++) {

			final URL url = new URL(
					"http://times.kaist.ac.kr/pdf/list.php?hosu="
							+ URLEncoder.encode(i + "", "UTF-8"));
			final URLConnection connection1 = url.openConnection();
			connection1.setConnectTimeout(10000000);
			connection1.setReadTimeout(10000000);
			connection1.addRequestProperty("User-Agent", "Mozilla/5.0");

			final Scanner reader = new Scanner(connection1.getInputStream(),
					"euc-kr");
			while (reader.hasNextLine()) {
				String line = reader.nextLine();

				String myeonStandard = "<TD align=middle><FONT style=FONT-SIZE: 9pt><span style=font-size:9pt;>";
				String gooboonStandard = "<TD align=middle><span style=font-size:9pt;>&nbsp;";
				String gisaStandard = "<TD><FONT style=FONT-SIZE: 9pt><span style=font-size:9pt;>";

				if (line.contains(myeonStandard)) {
					String myeon = line.split(myeonStandard)[1]
							.split("</span></FONT></TD>")[0];
	

					System.out.print(i);
					out.write(i + "\t");

					System.out.print("//" + myeon);
					out.write(myeon + "\t");

				}
				if (line.contains(gooboonStandard)) {
					if (line.split(gooboonStandard)[1].equals("</span></TD>")) {
						System.out.print("//" + "미기재");
						out.write("미기재" + "\t");
					} else {
						String gooboon = line.split(gooboonStandard)[1]
								.split("</span></TD>")[0];
						System.out.print("//" + gooboon);
						out.write(gooboon + "\t");
					}

				}
				if (line.contains(gisaStandard)) {
					String gisa = line.split(gisaStandard)[1];
					gisa = gisa.replace("<br />", "");
					gisa = gisa.replace("-", "");
					System.out.print("//"
							+ gisa.replaceAll("</span></FONT></TD>", "") + "/");
					out.write(gisa.replaceAll("</span></FONT></TD>", "") + "\t");

					while (!line.contains("</span></FONT></TD>")) {
						line = reader.nextLine();
						
						line = line.replace("-", "");
						line = line.replace("<br />", "");
						System.out.print(line.replaceAll("</span></FONT></TD>",
								"") + "/");
						out.write(line.replaceAll("</span></FONT></TD>", "")+"\t");
					}
					System.out.println("");
					out.write("\r\n");

				}
			}
			reader.close();

		}
		out.close();
	}
}
