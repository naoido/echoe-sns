<template>
  <v-container class="py-8 px-6"
               fluid
  >
    <v-row>
      <template v-for="echo in echoes" :key="echo.id">
        <v-list-item>
          <template v-slot:prepend>
            <v-avatar color="grey-darken-1">
            </v-avatar>
          </template>

          <v-list-item-title :title="echo.userName"></v-list-item-title>
          <v-list-item-subtitle :title="echo.echo"></v-list-item-subtitle>
        </v-list-item>
      </template>
    </v-row>
  </v-container>
</template>

<script>
import axios from "@/plugins/axios";

export default {
  name: "home-component",
  data: () => ({
    index: 0,
    echoes: [],
    images: []
  }),
  async mounted() {
    if (localStorage.getItem("token")) {
      const ec = (await axios.get(`/api/api/v1/echo?index=${this.index}`)).data;
      this.echoes.unshift(ec)
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
  }
}
</script>