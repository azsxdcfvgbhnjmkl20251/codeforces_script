fork do
  exec <<~CMD
    node -e "
      console.log('Hi from nested nodejs!');
    "
  CMD
end
