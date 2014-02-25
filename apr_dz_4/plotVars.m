% plotaj varijable stanja sustava

figure(1);
clf;
hold on;

arg_list = argv();

values = load('-ascii', arg_list{1});
step = str2double(arg_list{2});
interval = str2double(arg_list{3});

[m n] = size(values);
cmap = hsv(m + 1);

x = [0:step:interval];

for i = 1:m
	plot(x, values(i, :), 'Color', cmap(i, :))
end

title('Varijable stanja');
xlabel('Vrijeme t');
ylabel('Varijable X');

legendString = [];

for i = 1:m
	legendString = [legendString; strcat('X', int2str(i))];
end

legend(legendString);

saveas(1, arg_list{4}, 'png');
