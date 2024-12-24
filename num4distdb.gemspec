Gem::Specification.new do |s|
  s.name          = 'num4distdb'
  s.version       = '0.0.1'
  s.date          = '2023-12-23'
  s.summary       = "num for distribution db."
  s.description   = "numerical solution for distribution database by mongodb."
  s.platform      = 'java'
  s.authors       = ["siranovel"]
  s.email         = "siranovel@gmail.com"
  s.homepage      = "http://github.com/siranovel/distgraph"
  s.license       = "MIT"
  s.files       = ["LICENSE", "Gemfile", "CHANGELOG.md"]
  s.files       += Dir.glob("{lib,ext}/**/*")
  s.extensions  = %w[Rakefile]
  s.add_development_dependency 'rake', '~> 12.3', '>= 12.3.3'
  s.add_development_dependency 'rake-compiler', '~> 1.2', '>= 1.2.5'
end
