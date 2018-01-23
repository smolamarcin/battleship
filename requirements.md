| Requirement    | Done |
| :-------------:| ---- |
|Java FX simple GUI| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Network game, client-server architecture| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Both players are human players| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|One game only| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|10x10 board| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Fleet consists of: 4-mast ship, 2 3-mast ships, 3 2-mast ships and 4 1-mast ships.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Game messages have configurable target: console (System.out, System.err) or logs or external printer.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/673432-200.png)
|We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png)
|Drawing the boards for a player (fleet board has player's fleet and where opponent shot, "seen" board has where player fired and what he has shot). | ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Placing the fleet - diagonal placing is disallowed, only horizontal and vertical.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Humans can place ships but they can also choose to randomize placement.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png)
|Ships cannot touch (no adjacent field to a ship can have a ship).| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Ships can be partially vertical and partially horizontal, if they have the length.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/673432-200.png)
|Firing the shot - choose a place, shoot. If you hit, you repeat the shot. You can repeat as many times as you hit.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|60% unit test code coverage (lines).| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/673432-200.png)
|Hitting the ship - hit happens when place chosen has enemy ship. Mark this part of ship as hit, ask for another shot. One can repeat the shot into already hit (or even sunken) ship, but this doesn't give the right to another shot.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Missing the ship - misses are marked on "seen" board. One can shoot twice in the same place if it's a miss.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Sinking the ship - if all masts of a ship are hit, ship sinks. Once the ship has sunk, mark all adjacent fields as "missed", since none of them can have a ship anyway.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png)
|Sinking last ship, that is, winning.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Nuke - thrice per game player chooses a 3x3 area and "nukes" it, that is, all ships within take damage as if shot. This is done in addition to normal shot. Only 4-mast ship has nukes, so once they are sunk, nukes cannot be used.|![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/673432-200.png)
|Holy master - everything on master is holy, this is what is being checked by customer| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Reviewers - pull-requests to master that are handled by CI server are then reviewed internally by teammates| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Outside reviewers - once team says yes, outsiders come in (each team chooses external reviewers). Two external reviewers need to say "code is OK" before it can be pulled to master.| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|All code is on GitHub| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Jenkins is used as CI server| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Maven is used by Jenkins and team| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Maven has Findbugs, JaCoCo and Checkstyle integration| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Dependency convergence must be 100%, verified with maven-enforcer plugin| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Sonar is used to keep quality gates (just internally is fine).| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
|Documentation ( CRC diagrams, Java docs)| ![alt text](https://d30y9cdsu7xlg0.cloudfront.net/png/127459-200.png) |
