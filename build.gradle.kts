plugins {
    id("java")
}

group = "com.excelmate"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")

    implementation("org.apache.poi:poi-ooxml:5.3.0")
}

tasks.test {
    useJUnitPlatform()
}
