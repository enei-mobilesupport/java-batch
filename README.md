# serviceinfo

## Eclipse Workspace

serviceinfo/projects
- serviceinfo.api-link
　　APIと管理者向け画面
- serviceinfo.csv-link
　　CSVファイル連携バッチ
- serviceinfo.api-stub
　　APIスタブ

All projects are gradle project.


## Vagrant環境

- [vagrant](https://vagrantup.com) - 2.0.1以上
- [virtualbox](https://www.virtualbox.org) - 5.2.2以上

```
cd serviceinfo/vagrant
vagrant up
```

### MySQL 192.168.9.9:3306
```
 ID/PW: mysql/mysql
```
Schema: 
 - serviceinfo 
 - idpdb

### スタブWebサーバー(Tomcat)
http://192.168.9.9:8080/manager
```
 ID/PW: manager/admin
```
