plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("com.discord4j:discord4j-core:3.2.5-SNAPSHOT")

}

tasks.test {
    useJUnitPlatform()
}