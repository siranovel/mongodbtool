require 'num4distdb'

class TestDistDb
    def initialize
        @mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
    end
    def test_dbs
        @mongo.dspdbs
    end
    def test_dspdistdbs
        ret = @mongo.dspdistdbs
        ret.each do |name|
            puts name
        end
    end
    def test_dspdistdts
        ret = @mongo.dspdistdts("asininv")
        ret.each do |map|
            p map
        end
    end
    def test_dropdist
        @mongo.dropdist("asininv")
        test_dspdistdbs
    end
end

test = TestDistDb.new
p "------- dbs -------"
test.test_dbs
p "------- dspdistdbs -------"
test.test_dspdistdbs
p "------- dspdistdts(asininv) -------"
test.test_dspdistdts
p "------- dropdist(asininv) -------"
test.test_dropdist

