require 'num4distdb'

class TestDistDb
    def initialize
        @mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
    end
    def test_crtasindist
        @mongo.crtasindist
    end
    def test_crtbebidist
        @mongo.crtbebidist(50, 0.1, 0.1)
    end
    def test_crtbidist
        @mongo.crtbidist(100, 0.6)
    end
    def test_crtcauchydist
        @mongo.crtcauchydist(0, 1)
    end
    def test_crtchi2dist
        @mongo.crtchi2dist(100)
    end
    def test_crtexpdist
        @mongo.crtexpdist(100)
    end
    def test_crtfdist
        @mongo.crtfdist(20, 120)
    end
    def test_crtgmdist
        @mongo.crtgmdist(20, 120)
    end
    def test_crtgndist
        @mongo.crtgndist(100)
    end
    def test_crtgudist
        @mongo.crtgudist(20, 5)
    end
    def test_crthgedist
        @mongo.crthgedist(15)
    end
    def test_crtladist
        @mongo.crtladist(20, 10)
    end
    def test_crtledist
        @mongo.crtledist(20, 10)
    end
    def test_crtlogdist
        @mongo.crtlogdist(20, 10)
    end
    def test_crtlogndist
        @mongo.crtlogndist
    end
    def test_crtndist
        @mongo.crtndist
    end
    def test_crtnkdist
        @mongo.crtnkdist(5, 10)
    end
    def test_crtparetodist
        @mongo.crtparetodist
    end
    def test_crtpodist
        @mongo.crtpodist(10)
    end
    def test_crttdist
        @mongo.crttdist(120)
    end
    def test_crttgldist
        @mongo.crttgldist(10, 20, 15)
    end
    def test_crtwbldist
        @mongo.crtwbldist(5, 10)
    end
    def test_crtzipfdist
        @mongo.crtzipfdist(5, 10)
    end
end

test = TestDistDb.new
p "------- crtasindist -------"
test.test_crtasindist
p "------- crtbebidist -------"
test.test_crtbebidist
p "------- crtbidist -------"
test.test_crtbidist
p "------- crtcauchydist -------"
test.test_crtcauchydist
p "------- crtchi2dist -------"
test.test_crtchi2dist
p "------- crtexpdist -------"
test.test_crtexpdist
p "------- crtfdist -------"
test.test_crtfdist
p "------- crtgmdist -------"
test.test_crtgmdist
p "------- crtgndist -------"
test.test_crtgndist
p "------- crtgudist -------"
test.test_crtgudist
p "------- crthgedist -------"
test.test_crthgedist
p "------- crtladist -------"
test.test_crtladist
p "------- crtledist -------"
test.test_crtledist
p "------- crtlogdist -------"
test.test_crtlogdist
p "------- crtlogndist -------"
test.test_crtlogndist
p "------- crtndist -------"
test.test_crtndist
p "------- crtnkdist -------"
test.test_crtnkdist
p "------- crtparetodist -------"
test.test_crtparetodist
p "------- crtpodist -------"
test.test_crtpodist
p "------- crttdist -------"
test.test_crttdist
p "------- crttgldist -------"
test.test_crttgldist
p "------- crtwbldist -------"
test.test_crtwbldist
p "------- crtzipfdist -------"
test.test_crtzipfdist

