| Optimistic         |  Realistic         |  Pesimistic         | Requirement    | 
| ------------- | ------------- | ------------- |:-------------:| 
|06.12.17|07.12.17|08.12.17|Project mantra followed (Git, Maven, test cases, etc.).|
|12.12.17|14.12.17|16.12.17|Java FX simple GUI|
|07.12.17|08.12.17|10.12.17|Network game, client-server architecture|
|24.12.17|25.12.17|26.12.17|Both players are human players|
|15.12.17|16.12.17|17.12.17|One game only(Game configuration, game starts)|
|10.12.17|11.12.17|12.12.17|10x10 board|
|13.12.17|14.12.17|15.12.17|Fleet consists of: 4-mast ship, 2 3-mast ships, 3 2-mast ships and 4 1-mast ships.|
|20.12.17|21.12.17|22.12.17|Winner has ships remaining while loser has none.|
|15.12.17|16.12.17|17.12.17|Game messages have configurable target: console (System.out, System.err) or logs or external printer.|
|15.12.17|16.12.17|17.12.17|We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.|
|16.12.17|17.12.17|18.12.17|Drawing the boards for a player (fleet board has player's fleet and where opponent shot, "seen" board has where player fired and what he has shot).|
|18.12.17|19.12.17|20.12.17|Placing the fleet - diagonal placing is disallowed, only horizontal and vertical. Humans can place ships but they can also choose to randomize placement. Ships cannot touch (no adjacent field to a ship can have a ship). Ships can be partially vertical and partially horizontal, if they have the length.|
|21.12.17|22.12.17|23.12.17|Firing the shot - choose a place, shoot. If you hit, you repeat the shot. You can repeat as many times as you hit.|
|24.12.17|25.12.17|27.12.17|Hitting the ship - hit happens when place chosen has enemy ship. Mark this part of ship as hit, ask for another shot. One can repeat the shot into already hit (or even sunken) ship, but this doesn't give the right to another shot.|
|24.12.17|25.12.17|27.12.17|Missing the ship - misses are marked on "seen" board. One can shoot twice in the same place if it's a miss.|
|24.12.17|25.12.17|27.12.17|Sinking the ship - if all masts of a ship are hit, ship sinks. Once the ship has sunk, mark all adjacent fields as "missed", since none of them can have a ship anyway.|
|24.12.17|25.12.17|27.12.17|Sinking last ship, that is, winning.|
|02.01.18|05.01.18|10.01.18|Nuke - thrice per game player chooses a 3x3 area and "nukes" it, that is, all ships within take damage as if shot. This is done in addition to normal shot. Only 4-mast ship has nukes, so once they are sunk, nukes cannot be used.|
|08.12.17|11.12.17|12.12.17|CI server - before anything gets pulled into master, it is integrated with master by CI server, it runs tests, checks, etc.|
|05.12.17|06.12.17|11.12.17|Jenkins (or equivalent) is used as CI server|
|05.12.17|05.12.17|05.12.17|Maven is used by Jenkins and team|
|06.12.17|07.12.17|08.12.17|Maven has Findbugs, JaCoCo and Checkstyle integration|
|07.12.17|08.12.17|09.12.17|Sonar is used to keep quality gates (just internally is fine).|
|06.12.17|07.12.17|08.12.17|Documentation should be provided, explaining program architecture (diagram is necessary, CRC diagrams are most welcome)|
