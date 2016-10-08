# Maze generation using Kruskal's algorithm with KotlinJS

Read about it: http://red-green-rewrite.github.io/2016/10/06/Kruskal-Kotlin-and-Hex-Tiles/

Online demo: http://red-green-rewrite.github.io/kotlinjs-hexagen/index.html

Initialize:
* ``__install.cmd`` (install node and python packages)
* ``npm run build`` (build the project)
* ``npm run serve`` (serve the output folder)

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
