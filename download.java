import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class download {
	
	public static void main(String[] args) throws IOException {
		//downLoad("https://www.312ii.com/shipin/18440.html");
	}
	public static void downLoad(String url) throws IOException {
		int mark=0;
		do{
		try{
		HttpURLConnection conn = null;
        
            URL realUrl = new URL(url);
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setReadTimeout(8000);
            conn.setConnectTimeout(8000);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = in.readLine()) != null){
                    buffer.append(line);
                }
                String result = buffer.toString();
                //subscriber是观察者，在本代码中可以理解成发送数据给activity
                //System.out.println(result+123);
                Document dc=Jsoup.parseBodyFragment(result);
                Elements el=dc.getElementsByTag("input");
                //System.out.println(el.get(el.size()-1).attr("data-clipboard-text"));
                //tl.add(el.get(el.size()-1).attr("data-clipboard-text"));
                FileWriter fw=new FileWriter("download.txt",true);
                fw.write(el.get(el.size()-1).attr("data-clipboard-text")+'\n');
                fw.close();
                mark=1;
                //System.out.println("&&&&&&&&&&&&&&");
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		}while(mark!=1);
	}
}
