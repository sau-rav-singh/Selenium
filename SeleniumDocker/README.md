# markhobson/maven-chrome

Docker image for Java automated UI tests.

Includes:

* JDK 21
* Maven 3.9.5
* Chrome 145.0.7632.109
* ChromeDriver 145.0.7632.77

Available on [Docker Hub](https://hub.docker.com/r/singhsaurav/seleniumdocker).

## Tags

The following Docker tags are available:

* `jdk-21`, `latest` [(jdk-21/Dockerfile)](jdk-21/Dockerfile)

## Maintaining

To upgrade the Docker images to use the latest version of Chrome, install [dctrl-tools](https://pkgs.org/download/dctrl-tools) and run:

```bash
./upgrade.sh chrome
```

To upgrade the Docker images to use the latest version of ChromeDriver:

```bash
./upgrade.sh chromedriver
```

Note that both of these scripts will also commit to Git.

## Tips

### Using with Selenium

Configure [Selenium](https://www.selenium.dev/) to launch Chrome in headless mode:

```java
ChromeOptions options = new ChromeOptions().addArguments("--headless=new");
WebDriver driver = new ChromeDriver(options);
```
### Chrome crashes

Chrome uses `/dev/shm` for runtime data which is 64MB by default under Docker. If this is not sufficient then this can cause [Chrome to crash](https://bugs.chromium.org/p/chromium/issues/detail?id=522853). Possible workarounds:

* Increase the size of `/dev/shm`
* Mount `/dev/shm` to the host's
* Start Chrome with the flag `--disable-dev-shm-usage`
