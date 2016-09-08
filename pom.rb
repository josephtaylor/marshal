project 'marshal', 'http://maven.apache.org' do

    model_version '4.0.0'
    id 'io.github.josephtaylor:marshal:0.0.1-SNAPSHOT'
    packaging 'jar'

    properties 'project.build.sourceEncoding' => 'UTF-8'

    jar 'org.processing:processing-core:3.0'
    jar 'com.thoughtworks.xstream:xstream:1.4.9'
    jar 'com.google.code.gson:gson:2.3.1'
    jar 'com.esotericsoftware.yamlbeans:yamlbeans:1.09'
    jar 'junit:junit:4.12', scope: 'test'

    plugin :compiler, '3.3', source: '1.8', target: '1.8'
    plugin :jar, '2.3.2', finalName: 'marshal'
    plugin :javadoc, '2.9.1', reportOutputDirectory: 'target/reference'
    plugin :dependency, '2.10' do
        execute_goals('copy-dependencies',
                      id: 'copy-dependencies',
                      phase: 'package',
                      outputDirectory: '${project.build.directory}/lib',
                      overWriteReleases: false,
                      overWriteSnapshots: false,
                      overWriteIfNewer: true,
                      excludeArtifactIds: 'processing-core,xmlpull,xpp3_min,junit')
    end

end
