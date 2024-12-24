require 'num4distdb'

mongo = Num4DistDBLIB::Num4DistDBInfoLib.new
ret = mongo.dspdistdbs
ret.each do |name|
    mongo.dropdist(name)
end

