<template>
  <v-card :width="300" class="mx-auto mt-10 pb-10">
    <v-card-title>Login!</v-card-title>
    <div class="d-flex flex-column">
      <v-btn @click="googleLogin" class="mt-4 mx-4" color="red">Google ログイン</v-btn>
    </div>
  </v-card>
</template>

<script>
import { signInWithPopup, GoogleAuthProvider } from "firebase/auth"
import { auth } from "@/plugins/firebaseConfig"

export default {
  name: 'login-form',
  methods: {
    googleLogin() {
      const provider = new GoogleAuthProvider()

      signInWithPopup(auth, provider).then(async result => {
        const token = await result.user.getIdToken(true)

        localStorage.setItem("userName", result.user.displayName)
        localStorage.setItem("userId", result.user.uid)
        localStorage.setItem("userImage", result.user.photoURL)

        localStorage.setItem("token", token)
        window.location.reload()
      })
    }
  }
}
</script>