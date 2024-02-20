## このプロジェクトの実行手順  

1.このリポジトリをcloneし、ローカルにダウンロードする  
> git clone https://github.com/naoido/echoe-sns.git

<br>

2.ルートディレクトリにfirebaseKey.jsonを作成し、Firebaseからkeyを取得する  

<br>

3.`front/echoe/src/plugins/firebaseConfig.js`の中身を自身のプロジェクトのconfigに変更する  

<br>  

4.front/echoe/に移動し、vuetifyをビルドする
> npm run build

<br> 

5.dockerを立ち上げる
> docker-compose up -build

<br>

5. 起動を確認する`http://localhost`にアクセスし、ホーム画面が表示されたら成功
