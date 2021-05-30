![build workflow](https://github.com/yugogamer/minecraft_mod_serverinfo/actions/workflows/gradle.yml/badge.svg)

# minecraft_mod_serverinfo

 A small API mod for minecraft server

## Road

### Status

/status

return information about the server

response :

```json
{
  "name": "Server",
  "version": "1.16.5",
  "description": "Dev - New World",
  "maxplayer": 8,
  "currentplayer": 1,
  "whitelist": false,
  "modded": true,
  "hardcore": false,
  "gametype": "creative",
  "pvp": true
}
```

### PlayerList

/playerlist

Return username of current online player

response :

```json
{
  "players": [
    "Dev"
  ],
  "maxplayer": 8,
  "currentplayer": 1
}
```

### SendMessage

/sendmessage

query : 

| query    | value                    |
| -------- | ------------------------ |
| username | The name of the sender   |
| message  | The message tu send      |
| player   | Username of the receiver |

Return information about the execution

response :

```json
{
  "status": "succes",
  "playerGetMessage": true
}
```

### ExecuteCommand

/admin/execute

header :

| key     | value                  |
| ------- | ---------------------- |
| adminid | the id in the config   |
| command | the command to execute |

response :

```json

```
