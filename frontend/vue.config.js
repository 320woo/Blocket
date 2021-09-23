
// Vue3 관련 설정 파일
const path = require('path')

module.exports = {
  configureWebpack: {
    resolve: {
      alias: {
        '@': path.join(__dirname, 'src/')
      }
    }
  },
  
  devServer: {
    
    proxy: {
      '/blocket': {
        target: 'http://localhost:8080'
      }
    },
    
    historyApiFallback: true,
    hot: true,
    port: 3000
  },
  
  
}
