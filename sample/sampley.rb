require 'num4distdb'

#p "------- crtasindist -------"
#mongo = Num4DistDBLIB::Num4DistDBCrtLib.new
#mongo.crtasindist

mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
mongo.dspdistdts("asininv")
#mongo.dspdbs

#ret = mongo.dspdistdts("asininv")
#ret.each do |map|
#    p map
#end

