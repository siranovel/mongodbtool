Gem::Specification.new do |s|
  s.name          = 'num4distdb'
  s.version       = '0.1.1'
  s.date          = '2025-05-15'
  s.summary       = "num for distribution db."
  s.description   = "numerical solution for distribution database by mongodb."
  s.authors       = ["siranovel"]
  s.email         = "siranovel@gmail.com"
  s.homepage      = "http://github.com/siranovel/distgraph"
  s.license       = "MIT"
  s.required_ruby_version = ">= 3.0"
  s.files       = ["LICENSE", "Gemfile", "CHANGELOG.md"]
  s.files       += Dir.glob("{lib,ext}/**/*")
  s.extensions  = %w[Rakefile]
  s.add_dependency 'rake', '~> 13', '>= 13.0.6'
  s.add_development_dependency 'rake-compiler', '~> 1.3', '>= 1.3.0'
end
