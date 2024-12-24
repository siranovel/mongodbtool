require 'java'
require 'num4distdb.jar'
require 'mongo-java-driver-3.12.9.jar'
require 'commons-math3-3.6.1.jar'

java_import 'Num4DistDBInfo'
java_import 'Num4DistDBCrt'
java_import 'java.util.List'
java_import 'java.util.Map'

# mongo tool
module Num4DistDBLIB
    class Num4DistDBInfoLib
        def initialize(uri="mongodb://localhost:27017")
            @mongo = Num4DistDBInfo.new(uri)
        end
        # dbs情報を表示
        #
        # @overload dspdbs()
        #   @return [Array] dbs情報
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
        #   ret = mongo.dspdbs
        #   ret.each do |name|
        #       puts name
        #   end
        def dspdbs
            ret = @mongo.dspDBs()
            return ret.toArray().to_a
        end
        # distdb内のテーブル一覧を表示
        #
        # @overload dspdistdbs()
        #   @rerturn [Array] distdb内のテーブル一覧
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
        #   ret = mongo.dspdistdbs
        #   ret.each do |name|
        #       puts name
        #   end
        def dspdistdbs
            ret = @mongo.dspDistDBs()
            return ret.toArray().to_a
        end
        # コレクション内のデータ
        #
        # @overload dspdistdts(tblnm)
        #   @param [String] tblnm テーブル名
        #   @return [Array<Map>] テーブル内のデータ一覧
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
        #   ret = @mongo.dspdistdts("asininv")
        #   ret.each do |map|
        #       p map
        #   end
        def dspdistdts(tblnm)
            retRb = []
            ret = @mongo.dspDistDts(tblnm)
            n = ret.size
            n.times {|i|
                map = {}
                o = ret.get(i) # Map<String, Object>
                keys = o.keySet().to_a
                keys.each do |key|
                    map[key] = o.get(key)
                end
                retRb.push(map)
            }
            return retRb
        end
        # コレクションを削除
        #
        # @verload dropdist(tblnm)
        #   @param [String] tblnm テーブル名
        #   @return [void] 
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
        #   mongo.dropdist("asininv")
        def dropdist(tblnm)
            @mongo.dropDist(tblnm)
            return
        end
    end
    # 分布表の作成
    #  (Apache commoms math3使用)
    class Num4DistDBCrtLib
        def initialize(uri="mongodb://localhost:27017")
            @mongo = Num4DistDBCrt.new(uri)
        end
        # 逆正弦分布
        #
        # @overload crtasindist()
        #   @return [void] 逆正弦分布作成(db:distdb tbl:asininv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtasindist
        def crtasindist
            @mongo.crtAsinDist()
            return
        end
        # ベータ二項分布
        #
        # @overload crtbebidist(trials, alpha, beta)
        #   @param [int] trials trials
        #   @param [double] alpha alpha
        #   @param [double] beta beta
        #   @return [void] ベータ二項分布(db:distdb tbl:bebi)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtbebidist(50, 0.1, 0.1)
        def crtbebidist(trials, alpha, beta)
            @mongo.crtBeBiDist(trials, alpha, beta)
            return
        end
        # 二項分布
        #
        # @overload crtbidist(trials, mu)
        #   @param [int] trials trials
        #   @param [double] mu mu
        #   @return [void] 二項分布(db:distdb tbl:biinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.mongo.crtbidist(100, 0.6)
        def crtbidist(trials, mu)
            @mongo.crtBiDist(trials, mu)
            return
        end
        # コーシー分布
        #
        # @overload crtcauchydist(median, scale)
        #   @param [double] median median
        #   @param [double] scale scale
        #   @return [void] コーシー分布(db:distdb tbl:cauchyinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtcauchydist(0, 1)
        def crtcauchydist(median, scale)
            @mongo.crtCauchyiDist(median, scale)
            return
        end
        # 階２乗分布
        #
        # @overload crtchi2dist(df)
        #   @param [double] df df
        #   @return [void] 階２乗分布(db:distdb tbl:chi2inv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.mongo.crtchi2dist(100)
        def crtchi2dist(df)
            @mongo.crtChi2Dist(df)
            return
        end
        # 指数分布
        #
        # @overload crtexpdist(mean)
        #   @param [double] mean mean
        #   @return [void] 指数分布(db:distdb tbl:expinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtexpdist(100)
        def crtexpdist(mean)
            @mongo.crtExpDist(mean)
            return
        end
        # F分布
        #
        # @overload crtfdist(nf, df)
        #   @param [double] nf nf
        #   @param [double] df df
        #   @return [void] F分布(db:distdb tbl:finv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtfdist(20, 120)
        def crtfdist(nf, df)
            @mongo.crtFDist(nf, df)
            return
        end
        # ガンマ分布
        #
        # @overload crtgmdist(shape, scale)
        #   @param [double] shape shape
        #   @param [double] scale scale
        #   @return [void] ガンマ分布(db:distdb tbl:gminv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtgmdist(20, 120)
        def crtgmdist(shape, scale)
            @mongo.crtGmDist(shape, scale)
            return
        end
        # スミルノフ・グラブス分布
        #
        # @overload crtgndist(df)
        #   @param [double] df df
        #   @return [void] スミルノフ・グラブス分布(db:distdb tbl:gninv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtgndist(100)
        def crtgndist(df)
            @mongo.crtGnDist(df)
            return
        end
        # ガンベル分布
        #
        # @overload crtgudist(mu, beta)
        #   @param [double] mu mu
        #   @param [double] beta beta
        #   @return [void] ガンベル分布(db:distdb tbl:guinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtgudist(20, 5)
         def crtgudist(mu, beta)
            @mongo.crtGuDist(mu, beta)
            return
        end
        # 超幾何分布
        #
        # @overload crthgedist(populationSize)
        #   @param [int] populationSize populationSize
        #   @return [void] 超幾何分布(db:distdb tbl:hgeinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crthgedist(15)
        def crthgedist(populationSize)
            @mongo.crtHgeDist(populationSize)
            return
        end
        # ラプラス分布
        #
        # @overload crtladist(mu, beta)
        #   @param [double] mu mu
        #   @param [double] beta beta
        #   @return [void] ラプラス分布(db:distdb tbl:lainv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtladist(20, 10)
        def crtladist(mu, beta)
            @mongo.crtLaDist(mu, beta)
            return
        end
        # レヴェ分布
        #
        # @overload crtledist(mu, c)
        #   @param [double] mu mu
        #   @param [double] c c
        #   @return [void] レヴェ分布(db:distdb tbl:leinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtledist(20, 10)
        def crtledist(mu, c)
            @mongo.crtLeDist(mu, c)
            return
        end
        # ロジスティック分布
        #
        # @overload crtlogdist(mu, s)
        #   @param [double] mu mu
        #   @param [double] s s
        #   @return [void] ロジスティック分布(db:distdb tbl:loginv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtlogdist(20, 10)
        def crtlogdist(mu, s)
            @mongo.crtLogDist(mu, s)
            return
        end
        # 対数正規分布
        #
        # @overload crtlogndist()
        #   @return [void] 対数正規分布(db:distdb tbl:lognorminv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtlogndist
        def crtlogndist
            @mongo.crtLognDist()
            return
        end
        # 正規分布
        #
        # @overload crtndist()
        #   @return [void] 正規分布(db:distdb tbl:norminv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtndist
        def crtndist
            @mongo.crtNDist()
            return
        end
        # 仲上分布
        #
        # @overload crtnkdist(mu, omega)
        #   @param [double] mu mu
        #   @param [double] omega omega
        #   @return [void] 仲上分布(db:distdb tbl:nginv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtnkdist(5, 10)
        def crtnkdist(mu, omega)
            @mongo.crtNkDist(mu, omega)
            return
        end
        # パレット分布
        #
        # @overload crtparetodist()
        #   @return [void] パレット分布(db:distdb tbl:gpinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtparetodist
        def crtparetodist
            @mongo.crtParetoDist()
            return
        end
        # ポイソン分布
        #
        # @overload crtpodist(lambda)
        #   @param [double] lambda lambda
        #   @return [void] ポイソン分布(db:distdb tbl:poinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtpodist(10)
        def crtpodist(lambda)
            @mongo.crtPoDist(lambda)
            return
        end
        # T分布
        #
        # @overload crttdist(df)
        #   @param [double] df df
        #   @return [void] T分布(db:distdb tbl:tinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crttdist(120)
        def crttdist(df)
            @mongo.crtTDist(df)
            return
        end
        # トライアングル分布
        #
        # @overload crttgldist(a, b, c)
        #   @param [double] a a
        #   @param [double] b b
        #   @param [double] c c
        #   @return [void] トライアングル分布(db:distdb tbl:tglinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crttgldist(10, 20, 15)
        def crttgldist(a, b, c)
            @mongo.crtTglDist(a, b, c)
            return
        end
        # ワイブル分布
        #
        # @overload crtwbldist(alpha, beta)
        #   @param [double] alpha alpha
        #   @param [double] beta beta
        #   @return [void] ワイブル分布(db:distdb tbl:tglinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtwbldist(5, 10)
        def crtwbldist(alpha, beta)
            @mongo.crtWblDist(alpha, beta)
            return
        end
        # Zipf分布
        #
        # @overload crtzipfdist(n, exponent)
        #   @param [int] n n
        #   @param [double] exponent exponent
        #   @return [void] Zipf分布(db:distdb tbl:zipfinv)
        # @example
        #   mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
        #   mongo.crtzipfdist(5, 10)
        def crtzipfdist(n, exponent)
            @mongo.crtZipfDist(n, exponent)
            return
        end
    end
    
end

