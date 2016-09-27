# Template to start your KotlinJS application

(because I have no time to learn how to build Yeoman generator)

Initialize:
* ``__install.cmd`` (install node and python packages)

Includes:
* gradle (compiles Kotlin to JS)
* webpack (bundles all together)
* watch (helps keep all things compiled all the time
* intellij project (so you can benefit from auto-completion)
* babel & typescript (when you need some plumbing)
* stylus (opinionated choice for css replacement)

Libraries:
* bootstrap
* jquery

Scripts:
* ``npm run gradle`` (runs gradle once)
* ``npm run webpack`` (runs webpack once)
* ``npm run build`` (runs gradle and webpack once)
* ``npm run release`` (runs gradle and webpack in release mode once)
* ``npm run serve`` (server output folder)
* ``watch`` (runs webpack, http server and gradle in watch mode)

Renaming (files that contain project name):
* ``package.json``
* ``settings.gradle``
* ``src/html/index.html``

Renaming (if you intend to use IntelliJ)
* ``.idea/.name``
* ``.idea/modules.xml``
* ``template-kotlinjs-gradle-webpack.iml``
