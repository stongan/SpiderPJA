import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.xml.soap.Node;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

//import com.emar.core.httpClient.HttpclientUtil;  

public class GetProxyIP {

	private List<String> iplibset = new ArrayList<String>();
	private List<Integer> portlibset = new ArrayList<Integer>();

	public void checkIp(String urlgrap) {
		String line = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader("iplib4"));
			line = in.readLine();
			while (line != null) {
				String[] arr = line.split("\\|");
				// System.out.println(arr[0] + "|" + arr[1]);
				iplibset.add(arr[0]);
				portlibset.add(Integer.parseInt(arr[1]));
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter writer = null;
		try {
			writer = new FileWriter(ValidIpName, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int count = 0;
		for (int i = 0; i < iplibset.size(); i++) {
			try {
				URL url = new URL(urlgrap);
				// �������������
				InetSocketAddress addr = new InetSocketAddress(iplibset.get(i),
						portlibset.get(i));
				// Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); // Socket ����
				Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http ����
				// Authenticator.setDefault(new MyAuthenticator("username",
				// "password"));// ���ô�����û�������
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection(proxy);// ���ô������
				connection
						.setRequestProperty("User-Agent",
								"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				connection.setConnectTimeout(10000);
				if (connection.getResponseCode() == 200) {
					// System.out.println(iplibset.get(i) + ":" +
					// portlibset.get(i) + "|" + connection.getReadTimeout());
					// System.out.println(iplibset.get(i) + ":" +
					// portlibset.get(i) + "|" +
					// connection.getConnectTimeout());
					System.out.println(iplibset.get(i) + ":"
							+ portlibset.get(i) + "|"
							+ connection.getResponseCode());
					writer.write(iplibset.get(i) + "|" + portlibset.get(i)
							+ "\n");
					writer.flush();
					count++;
				}
				// InputStreamReader in = new InputStreamReader(
				// connection.getInputStream());
				// BufferedReader reader = new BufferedReader(in);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count:" + count);
	}

	public void getIP() {
		String URLSet[] = new String[300];
//		String tmp = "http://www.kuaidaili.com/free/inha/";
		String tmp = "http://www.kuaidaili.com/free/outha/";
		for (int i = 0; i < 300; i++) {
			URLSet[i] = tmp + (i + 1) + "/";
		}
		for (int i = 0; i < 300; i++) {
			getContent(URLSet[i]);
		}
	}
	public void getContent(String URLnow2) {
		Map<String, String> ret = new HashMap<String, String>();
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter("iplib0319", true);

			Connection con = Jsoup
					.connect(URLnow2)
					// .data("query", "Java")
					.userAgent(
							"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)")
					.timeout(0);
			/*
			 * .ignoreHttpErrors(true) .followRedirects(true)
			 */
			Connection.Response resp = con.execute();
			Document doc = null;
			if (resp.statusCode() == 200) {
				doc = con.get();
			}
			// System.out.println(doc);
			// Elements list =
			// doc.select("table[class*=table table-bordered table-striped]");
			Elements list = doc.select("td");
			// System.out.println(list);
			for (int j = 0; j < list.size(); j += 7) {
				String ip50 = list.get(j).unwrap().toString();
				String port51 = list.get(j + 1).unwrap().toString();
				writer.write(ip50 + "|" + port51 + "\n");
				writer.flush();
			}
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getValidIp(String fileIpName){
		getIP();
	}

	public GetProxyIP(){
	}
	
	private String ValidIpName = "IPLIST0304";
	public GetProxyIP(String fileIpName) {
		if(fileIpName != "")
			ValidIpName = fileIpName;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://www.idealista.com/";
		GetProxyIP aaa = new GetProxyIP();
		aaa.getIP();
		//		aaa.getIP0307();
		//aaa.checkIp(url);
//		System.out.println(isIp("222.33.22.3"));
	}
	
	public void getIP0307() {
		String URLSet[] = new String[300];
		String tmp = "http://www.xicidaili.com/nn";
		URLSet[0] = tmp;
		for (int i = 1; i < 300; i++) {
			URLSet[i] = tmp + "/" + (i);
		}
		for (int i = 0; i < 300; i++) {
			getContent0307(URLSet[i]);
		}
	}
	public void getContent0307(String URLnow2) {
		Map<String, String> ret = new HashMap<String, String>();
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter("iplib0307", true);

			Connection con = Jsoup
					.connect(URLnow2)
					// .data("query", "Java")
					.userAgent(
							"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0; BIDUBrowser 2.x)")
					.timeout(0);
			/*
			 * .ignoreHttpErrors(true) .followRedirects(true)
			 */
			Connection.Response resp = con.execute();
			Document doc = null;
			if (resp.statusCode() == 200) {
				doc = con.get();
			} else {
				return;
			}
				
//			System.out.println(doc);
			// Elements list =
			// doc.select("table[class*=table table-bordered table-striped]");
			Elements list = doc.select("td");
			// System.out.println(list);
			for (int j = 0; j < list.size(); j++) {
				//System.out.println(list.get(j));
				String ip50 = "";
				String port51 = "";
				Element next = list.get(j).nextElementSibling();
				if(!list.get(j).hasText())
					continue;
				Node tmp218 = (list.get(j).unwrap());
				if(tmp218 != null && isIp(tmp218.toString())){
					ip50 = tmp218.toString();
					port51 = String.valueOf(next.unwrap());
				}
				if(ip50 != "" && port51 != ""){
					System.out.println(ip50 + "|" + port51);
					writer.write(ip50 + "|" + port51 + "\n");
					writer.flush();
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isIp(String ip){
		int now = 0;
		int dotcount = 0;
		for(int i = 0; i < ip.length(); i++){
			if(ip.charAt(i) == '.'){
				dotcount++;
				if(dotcount > 3)
					return false;
				
				if(now >= 0 && now <= 255){
					now = 0;
					continue;
				} else {
					return false;
				}
			} else {
				int num = (ip.charAt(i) - '0');
				if(num >= 0 && num <= 9){
					now = now*10 + num;
				} else
					return false;
			}
		}
		if(now >= 0 && now <= 255){
			return true;
		} else {
			return false;
		}
	} 

}
