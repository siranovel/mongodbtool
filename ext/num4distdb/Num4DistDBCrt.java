public class Num4DistDBCrt {
    private String connectionString = null;
    public Num4DistDBCrt(String connectionString) {
        this.connectionString = connectionString;
    }
    // 逆正弦分布
    public void crtAsinDist() {
        CrtDistDt mongo = new CrtAsinDist(connectionString);

        mongo.crtDistDt("asininv");
    }
    // ベータ二項分布
    public void crtBeBiDist(int trials, double alpha, double beta) {
        CrtDistDt mongo = new CrtBeBiDist(connectionString, trials, alpha, beta);

        mongo.crtDistDt("bebi");
    }
    // 二項分布
    public void crtBiDist(int trials, double mu) {
        CrtDistDt mongo = new CrtBiDist(connectionString, trials, mu);

        mongo.crtDistDt("biinv");
    }
    // コーシー分布
    public void crtCauchyiDist(double median, double scale) {
        CrtDistDt mongo = new CrtCauchyiDist(connectionString, median, scale);

        mongo.crtDistDt("cauchyinv");
    }
    // 階２乗分布
    public void crtChi2Dist(double df) {
        CrtDistDt mongo = new CrtChi2Dist(connectionString,df);

        mongo.crtDistDt("chi2inv");
    }
    // 指数分布
    public void crtExpDist(double mean) {
        CrtDistDt mongo = new CrtExpDist(connectionString, mean);

        mongo.crtDistDt("expinv");
    }
    // F分布
    public void crtFDist(double nf, double df) {
        CrtDistDt mongo = new CrtFDist(connectionString, nf, df);

        mongo.crtDistDt("finv");
    }
    // ガンマ分布
    public void crtGmDist(double shape, double scale) {
        CrtDistDt mongo = new CrtGmDist(connectionString, shape, scale);

        mongo.crtDistDt("gminv");
    }
    // スミルノフ・グラブス分布
    public void crtGnDist(double df) {
        CrtDistDt mongo = new CrtGnDist(connectionString, df);

        mongo.crtDistDt("gninv");
    }
    // ガンベル分布
    public void crtGuDist(double mu, double beta) {
        CrtDistDt mongo = new CrtGuDist(connectionString, mu, beta);

        mongo.crtDistDt("guinv");
    }
    // 超幾何分布
    public void crtHgeDist(int populationSize) {
        CrtDistDt mongo = new CrtHgeDist(connectionString, populationSize);

        mongo.crtDistDt("hgeinv");
    }
    // ラプラス分布
    public void crtLaDist(double mu, double beta) {
        CrtDistDt mongo = new CrtLaDist(connectionString, mu, beta);

        mongo.crtDistDt("lainv");
    }
    // レヴェ分布
    public void crtLeDist(double mu, double c) {
        CrtDistDt mongo = new CrtLeDist(connectionString, mu, c);

        mongo.crtDistDt("leinv");
    }
    // ロジスティック分布
    public void crtLogDist(double mu, double s) {
        CrtDistDt mongo = new CrtLogDist(connectionString, mu, s);

        mongo.crtDistDt("loginv");
    }
    // 対数正規分布
    public void crtLognDist() {
        CrtDistDt mongo = new CrtLognDist(connectionString);

        mongo.crtDistDt("lognorminv");
    }
    // 正規分布
    public void crtNDist() {
        CrtDistDt mongo = new CrtNDist(connectionString);

        mongo.crtDistDt("norminv");
    }
    // 仲上分布
    public void crtNkDist(double mu, double omega) {
        CrtDistDt mongo = new CrtNkDist(connectionString, mu, omega);
 
        mongo.crtDistDt("nginv");
    }
    // パレット分布
    public void crtParetoDist() {
        CrtDistDt mongo = new CrtParetoDist(connectionString);

        mongo.crtDistDt("gpinv");
    }
    // ポイソン分布
    public void crtPoDist(double lambda) {
        CrtDistDt mongo = new CrtPoDist(connectionString, lambda);

        mongo.crtDistDt("poinv");
    }
    // T分布
    public void crtTDist(double df) {
        CrtDistDt mongo = new CrtTDist(connectionString, df);

        mongo.crtDistDt("tinv");
     }
    // トライアングル分布
    public void crtTglDist(double a, double b, double c) {
        CrtDistDt mongo = new CrtTglDist(connectionString, a, b, c);

        mongo.crtDistDt("tglinv");
    }
    // ワイブル分布
    public void crtWblDist(double alpha, double beta) {
        CrtDistDt mongo = new CrtWblDist(connectionString, alpha, beta);

        mongo.crtDistDt("wblinv");
    }
    // Zipf分布
    public void crtZipfDist(int nElm, double exponent) {
        CrtDistDt mongo = new CrtZipfDist(connectionString, nElm, exponent);

        mongo.crtDistDt("zipfinv");
    }
    /*********************************/
    /* interface define              */
    /*********************************/
    /*********************************/
    /* Class define                  */
    /*********************************/
}

