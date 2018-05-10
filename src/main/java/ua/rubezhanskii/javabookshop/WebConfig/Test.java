package ua.rubezhanskii.javabookshop.WebConfig;



public class Test  {
    public static void main(String[] args) throws  Exception {


}




//    private  void scriptRunner(){
//
//        String aSQLScriptFilePath = "sql/script.sql";
//        StringBuilder builder=new StringBuilder();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javazone","root","qaz123");
//            // Initialize object for ScripRunner
//            ScriptRunner sr = new ScriptRunner(con, true, false);
//
//            // Give the input file to Reader
//            Reader reader = new BufferedReader(
//                    new FileReader(aSQLScriptFilePath));
//
//            // Exctute script
//            sr.runScript(reader);
//        int x=0;
//        char s;
//        while ((x=reader.read())!=-1){
//          s=(char)x;
//          builder.append(s);
//
//        }
//
//        } catch (Exception e) {
//            System.err.println("Failed to Execute" + aSQLScriptFilePath
//                    + " The error is " + e.getMessage());
//        }
//        System.out.println("------------------------"+ builder.toString()+"---------------------");
//    }
}
//    public void HTTCONN(){
//
//        // Create a trust manager that does not validate certificate chains
//        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//            public void checkClientTrusted(X509Certificate[] certs, String authType) {
//            }
//            public void checkServerTrusted(X509Certificate[] certs, String authType) {
//            }
//        } };
//        // Install the all-trusting trust manager
//        final SSLContext sc = SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, new java.security.SecureRandom());
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        };
//
//        // Install the all-trusting host verifier
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//
//        URL url = new URL("https://www.apress.com");  //);https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=3CDEIBVEGR0SU75T");
//        URLConnection con = url.openConnection();
//        final Reader reader = new InputStreamReader(con.getInputStream());
//        final BufferedReader br = new BufferedReader(reader);
//        String line = "";
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//        br.close();
//
//    }

