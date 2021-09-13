
// Vue3 관련 설정 파일
module.exports = {
  devServer: {
    
    proxy: {
      '/cowdog': {
        target: 'http://localhost:8080'
      }
    },
    
    historyApiFallback: true,
    hot: true,
    port: 3000
  },
  
  
}
