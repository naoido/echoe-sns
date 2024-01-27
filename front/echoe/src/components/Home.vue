<template>
  <v-container class="py-8 px-6"
               fluid
  >
      <v-list>
        <v-list-item v-for="echo in echoes" :key="echo.id" class="mb-5" lines="three">
            <v-avatar color="grey-darken-1">
              <v-img :src="echo.image"/>
            </v-avatar>
            <v-list-item-title  color="grey-darken-1" class="d-inline ml-4">{{echo.userName}}</v-list-item-title>
          <v-spacer></v-spacer>

          <v-list-item-subtitle  color="grey-darken-2" class="mt-7">{{echo.echo}}</v-list-item-subtitle>
          <v-divider/>
        </v-list-item>
      </v-list>
  </v-container>
  <v-dialog width="500">
    <template v-slot:activator="{ props }">
      <v-row absolute style="bottom: 150px; position: fixed; width: 100vw; display: grid; justify-content: center; margin: 0 auto;">
        <v-col>
          <v-btn v-bind="props" text="Echo"/>
        </v-col>
      </v-row>
    </template>

    <template v-slot:default="{ isActive }">
      <v-card title="新しいEchoを作成">
        <v-text-field label="Echo" class="mt-3 px-5" v-model="echo"></v-text-field>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
              text="Send Echo"
              rounded="xl"
              color="white"
              class="bg-blue"
              @click="isActive = false; sendEcho()"
          />
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import axios from "@/plugins/axios";

const userDetails = new Map();

export default {
  name: "home-component",
  data: () => ({
    index: 0,
    echoes: [],
    images: [],
    echo: ""
  }),
  async mounted() {
    if (localStorage.getItem("token")) {
      const ec = (await axios.get(`/api/api/v1/echo?index=${this.index}`)).data;
      ec.reverse()

      for (const obj of ec) {
        if (!userDetails.has(obj.userId)) {
          const profile = (await axios.get("/api/api/v1/profile/" + obj.userId)).data
          userDetails.set(obj.userId, profile)
        }
        const detail = userDetails.get(obj.userId)
        obj.image= detail.image
        obj.userName = detail.userName

        this.echoes.unshift(obj)
      }

      console.log(this.echoes[0])
      console.log(this.echoes[0].userName)

      this.index++
    } else {
      this.echoes.push({
        id: 1,
        userId: "",
        echo: "ログインしてください。",
        createdAt: ""
      })
    }
  },
  methods: {
    async sendEcho() {
      await axios.post("/api/api/v1/echo?echo="+this.echo).then(() => {
        this.echoes.unshift({
          userId: localStorage.getItem("token"),
          userName: localStorage.getItem("userName"),
          echo: this.echo,
          image: localStorage.getItem("userImage"),
          createdAt: new Date()
        })
        this.echo = ""
      })
    }
  }
}
</script>