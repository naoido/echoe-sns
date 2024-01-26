<template>
  <v-card :width="300" class="mx-auto mt-10 pb-10">
    <v-card-title>Login!</v-card-title>
    <div class="d-flex flex-column">
      <v-btn @click="googleLogin" class="mt-4 mx-4" color="red">Google ログイン</v-btn>
      <v-btn @click="get" class="mt-4 mx-4" color="blue">send</v-btn>
    </div>
  </v-card>
</template>

<script>
import { signInWithPopup, GoogleAuthProvider } from "firebase/auth"
import { auth } from "@/plugins/firebaseConfig"
import axios from "@/plugins/axios";

export default {
  name: 'login-form',
  methods: {
    googleLogin() {
      const provider = new GoogleAuthProvider()

      signInWithPopup(auth, provider).then(async result => {
        const token = await result.user.getIdToken(true)

        localStorage.setItem("token", token)
        window.location.reload()
      }).catch(error => {
        console.log(error)
        this.errorMessage = error.message
        this.showError = true
      })
    },
    async get() {
      await axios.get("https://naoido.com/api/api/v1/echo/")
    }
  }
}
</script>